# Sumner Bradley
import datetime


class Like:  # Like object which is used by the Post object
    def __init__(self, username):
        self.username = username
        self.date = datetime.date
        self.time = datetime.time

    def to_json(self):
        temp = '{"date":"' + self.date + '","time":"' + self.time + '",'
        temp += '"username":"' + self.username + '"}'

        return temp
