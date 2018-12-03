# Sumner Bradley
import datetime


class Like:  # Like object which is used by the Post object
    def __init__(self, username):
        self.username = username
        self.date = datetime.date.today()
        self.time = str(datetime.datetime.now())[str(datetime.datetime.now()).find(" "):
                                                 str(datetime.datetime.now()).find(".")]

    def to_json(self):
        temp = '{"date":"' + str(self.date) + '","time":"' + str(self.time) + '",'
        temp += '"username":"' + self.username + '"}'

        return temp
