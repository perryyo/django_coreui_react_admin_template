# Django + COREUI React Admin Template

CoreUI + Django ver 

referance : https://github.com/coreui/coreui-free-react-admin-template

## Installation

### Common Installation
````
pip install -r requirements.txt

cd frontend
npm install

````

#### Development
```
cd frontend
npm run dev
```

#### Production
````
cd frontend
npm run build
````


### Run
#### Development
```
python manage.py runserver
```
#### Production
```
python manage.py runserver --setting=backend.settings.development
``` 

### Docker
```
docker pull kaniz/django_coreui
docker run -d -p 8000:8000 kaniz/django_coreui 
```