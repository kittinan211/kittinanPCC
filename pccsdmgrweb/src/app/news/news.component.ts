import { Component, OnInit } from '@angular/core';
import { FileService } from '../services/file.service';
import { HttpErrorResponse, HttpEvent, HttpEventType } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { NgForm } from '@angular/forms';
import { filenews } from '../services/filenews';
import { TokenStorageService } from '../services/token-storage.service';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common'

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css']
})
export class NewsComponent implements OnInit {
  filenames: string[] = [];
  filenamesProgressnew: string[] = [];
  allfilenames: filenews[];
  fileStatus = { status: '', requestType: '', percent: 0 };
  formData = new FormData();
  filenews: filenews;
  text: filenews;
  getname: any;
  date:Date;
  dateText:string;
  form: any = {};


  constructor(private fileService: FileService ,private tokenStorage: TokenStorageService ,private router: Router,private datepipe: DatePipe) { }


  
  ngOnInit() { 
    if (this.tokenStorage.getToken()) {
      this.getname =  this.tokenStorage.getUser().name

      this.date=new Date();
      this.date.setFullYear(this.date.getFullYear()+543);
      this.dateText = this.datepipe.transform(this.date, 'dd/MM/yyyy');
      this.getnews();
    }else{
      this.router.navigate(['../../']);
    }
    
  }
  
  public getnews(): void {
    this.fileService.allnews().subscribe(
      (response: filenews[]) => {
        this.allfilenames = response;
        console.log(this.allfilenames);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


  public getnewspdf(id): void {
    this.fileService.allpdf(id).subscribe(
      (response: string[]) => {
        this.filenames = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onOpenModalNews(mode: string , filenews:filenews): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addNewsModal');
    }

    if (mode === 'list') {
      this.text = filenews;
      button.setAttribute('data-target', '#listNewsModal');
      this.getnewspdf(filenews.newsid)
    }

    container.appendChild(button);
    button.click();
  }


  onUploadFiles(files: File[]): void {
    for (const file of files) { 
      this.formData.append('files', file, file.name); 
      this.filenamesProgressnew.push(file.name);
    }
  }

  addnews(filenewsN: filenews): void {

    this.fileService.addnews(filenewsN).subscribe(
      data => {
        this.UploadFiles(data);
      },
      err => {
        console.log(err);
      }
    );
  }
  datadismiss(): void {
window.location.reload();
  }

  

  UploadFiles(data): void {
    this.fileService.upload(this.formData,data).subscribe(
      event => {
        this.resportProgress(event);
        this.formData = new FormData();
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }

  // define a function to download files
  onDownloadFile(filename: string): void {
    this.fileService.download(filename).subscribe(
      event => {
        console.log(event);
        this.resportProgress(event);
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }

  private resportProgress(httpEvent: HttpEvent<string[] | Blob>): void {
    switch(httpEvent.type) {
      case HttpEventType.UploadProgress:
        this.updateStatus(httpEvent.loaded, httpEvent.total!, 'Uploading... ');
        break;
      case HttpEventType.DownloadProgress:
        this.updateStatus(httpEvent.loaded, httpEvent.total!, 'Downloading... ');
        break;
      case HttpEventType.ResponseHeader:
        console.log('Header returned', httpEvent);
        break;
      case HttpEventType.Response:
        if (httpEvent.body instanceof Array) {
          this.fileStatus.status = 'done';
          for (const filename of httpEvent.body) {
            this.filenames.unshift(filename);
          }
        } else {
          saveAs(new File([httpEvent.body!], httpEvent.headers.get('File-Name')!, 
                  {type: `${httpEvent.headers.get('Content-Type')};charset=utf-8`}));
          // saveAs(new Blob([httpEvent.body!], 
          //   { type: `${httpEvent.headers.get('Content-Type')};charset=utf-8`}),
          //    httpEvent.headers.get('File-Name'));
        }
        this.fileStatus.status = 'done';
        break;
        default:
          console.log(httpEvent);
          break;
      
    }
  }

  private updateStatus(loaded: number, total: number, requestType: string): void {
    this.fileStatus.status = 'progress';
    this.fileStatus.requestType = requestType;
    this.fileStatus.percent = Math.round(100 * loaded / total);
  }

  reloadPage(): void {
    this.router.navigate(['../../Employee']);
  }


  public searchEmployees(key: string): void {
    console.log(key);
    const results: filenews[] = [];
    for (const allfile of this.allfilenames) {
      if (allfile.newsname.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(allfile);
      }
    }
    this.allfilenames = results;
    if (results.length === 0 || !key) {
      this.getnews();
    }
  }
}
