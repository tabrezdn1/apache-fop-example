FOP example app
```
docker build -t foptopdf .
```
```
docker run --name fop-app -v ~/pdfOutput:/usr/src foptopdf
```