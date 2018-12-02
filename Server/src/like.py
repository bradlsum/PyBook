# Sumner Bradley
import datetime


class Like:
    def __init__(self, username):
        self.username = username
        self.date = datetime.date
        self.time = datetime.time

    def to_json(self):
        return ('{',
                '"date":' + '"' + self.date + '",',
                '"time":' + '"' + self.time + '",',
                '"username":' + '"' + self.username + '"',
                '}')
