module.exports = {
  purge: [],
  darkMode: 'class',
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {   
      colors:{
      vuejs:{
        222:"#7CDE9D",
        198:"#3DC6B1",
        183:"#17B7BD",
        241:"#A030F1",
        80:"#1E50FC",
        139:"#008BFF",
        254:"#FEECC4",
        153:"#995621"
        
      }, 
       Rcolors:{
        247:"#F704A5",
        33:"#F72119",
        136:"#F78803"
      },
      darkModecolors:{
        42:"#0F172A",
        59:"#1E293B",
        252:"#0DF5E3"
      }
     },

     width: {
      '400': '400px',
    },

    },
  },


  variants: {
    extend: {},
  },
  plugins: [
    require('flowbite/plugin')
  ],
}
