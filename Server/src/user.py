# Sumner Bradley
class User:  # User object which is used by the server object to store user information
    _ID = 0

    def __init__(self, first, last, email, username, password):
        self.id = User._ID
        User._ID += 1
        self.first_name = first
        self.last_name = last
        self.email = email
        self.username = username
        self.password = password

    def get_first(self):
        return self.first_name

    def set_first(self, first):
        self.first_name = first

    def get_last(self):
        return self.last_name

    def set_last(self, last):
        self.last_name = last

    def get_email(self):
        return self.email

    def set_email(self, email):
        self.email = email

    def get_password(self):
        return self.password

    def set_password(self, password):
        self.password = password

    def get_username(self):
        return self.username

    def set_username(self):
        self.username

    def to_json(self):
        temp = '{"id":"' + str(self.id) + '","first":"' + self.first_name + '",'
        temp += '"last":"' + self.last_name + '","email":"' + self.email + '",'
        temp += '"username":"' + self.username + '","password":"' + self.password + '"}'

        return temp
