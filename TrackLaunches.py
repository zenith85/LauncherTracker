from flask import Flask, request, jsonify
import threading
import datetime

app = Flask(__name__)

def log_data(name, app_name, date):
    with open("log.txt", "a") as log_file:
        log_file.write(f"Name: {name}, App: {app_name}, Date: {date}\n")

@app.route('/api/post', methods=['POST'])
def post_data():
    name = request.form.get('name', 'Unknown')
    app_name = request.form.get('app_name', 'Unknown')
    date = request.form.get('date', datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'))

    # Run log_data in a separate thread
    threading.Thread(target=log_data, args=(name, app_name, date)).start()

    return jsonify({"status": "success", "message": "Data received and logged in background"})

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=5800)
