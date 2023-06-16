/*const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})*/
// module.exports = {
//   devServer: {
//     proxy: 'http://localhost:8000'
//   }
// }
//개발 서버로 spring과 합쳐주는 부분
// const target = 'http://localhost:8000'
// module.exports - {
//   devServer:{
//     port: 8000,
//     proxy: {
//       '/api':{
//         target,
//         changeOrigin: true
//       }
//     }
//   }
// }
// proxy: {
//   '/api/*':{
//     target: 'http://localhost:8000',
//     changeOrigin: true,
//     pathRewrite: {
//       '^/api':''
//     }
//   }
// }
// module.exports = {
//   devServer: {
//       proxy: {
//           '/':{
//               "target":'http://localhost:8000', // Spring boot의 주소 및 포트
//               "pathRewrite":{'^/':''},
//               "changeOrigin":true,
//           }
//       }
//   }
// }
