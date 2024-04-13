## FOP example app

https://xmlgraphics.apache.org/fop/#demo


```
docker build -t foptopdf .
```
```
docker run --name fop-app -v ~/pdfOutput:/usr/src foptopdf
```