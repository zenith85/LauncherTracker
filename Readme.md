# Flask Server

This is a Flask-based server designed to handle multiple concurrent users. It uses Gunicorn to manage multiple worker processes and handles background tasks using threading.

## Requirements

Make sure you have the following installed:

- **Python 3.x**: Ensure Python 3 is installed on your system.
- **pip**: Python package installer.

### Install Required Python Packages

Before starting the server, install the necessary Python packages:

### Install requirements 
pip install -r requirements.txt

pip install flask gunicorn

### make sure that port 5800 is open if there any firewall

### Start the server example port 5800
gunicorn -w 20 -b 0.0.0.0:5800 Tracklaunches:app

### Start the Flask Development Server (For testing locally only)
### You can also run the Flask server in development mode (not recommended for production):
python server.py

This will start the Flask app on http://127.0.0.1:5000/.

Note: In production, Gunicorn should always be used over Flask’s built-in server.

## Accessing the API using android app
Add the class LaunchTracker.java to your android project
call the function in Oncreate using 
LauncherTracker.sendData("device name", "App name");
The date will be put by the LauncherTracker.java code



You can POST data to this endpoint, such as name, app_name, and date.

curl -X POST http://127.0.0.1:5000/api/post \
  -d "name=John Doe" \
  -d "app_name=MyApp" \
  -d "date=2025-02-11 14:00:00"

Background Task:
The server will handle the background task of logging the data without blocking the incoming request, using Python's threading module.

Storing Logs
Logs are saved to a file called log.txt in the server folder. You can modify this to save logs to a database or any other preferred storage.

Deployment
To deploy this server on a production environment, consider using a web server like NGINX in front of Gunicorn to handle static files and load balancing.

Troubleshooting
Gunicorn not starting: Ensure that Gunicorn is installed and you're using the correct Python version.
Permission errors: Ensure that your user has permission to write to log.txt.
License
This project is licensed under the MIT License - see the LICENSE file for details.


This file now includes everything about starting the server in one `README.md` for you to share or distribute. Let me know if anything else is needed!

