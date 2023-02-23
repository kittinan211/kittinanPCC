import { HttpClient, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { filenews } from '../services/filenews';

@Injectable({providedIn: 'root'})
export class FileService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  // define function to upload files
  upload(formData: FormData , filenews: filenews): Observable<HttpEvent<string[]>> {
    return this.http.post<string[]>(`${this.apiServerUrl}/file/upload/${filenews.newsid}/`, formData, {
      reportProgress: true,
      observe: 'events'
    });
  }

  addnews(filenews: filenews): Observable<filenews> {
    return this.http.post<filenews>(`${this.apiServerUrl}/file/addnews`, filenews);
  }

  allnews(): Observable<filenews[]> {
    return this.http.get<filenews[]>(`${this.apiServerUrl}/file/allnews`);
  }

  allpdf(id : String): Observable<String[]> {
    return this.http.get<String[]>(`${this.apiServerUrl}/file/allnewspdf/${id}/`);
  }

  // define function to download files
  download(filename: string): Observable<HttpEvent<Blob>> {
    return this.http.get(`${this.apiServerUrl}/file/download/${filename}/`, {
      reportProgress: true,
      observe: 'events',
      responseType: 'blob'
    });
  }
  
}
