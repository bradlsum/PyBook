# Sumner Bradley
import datetime
from src.like import Like


class Comment:
    _ID = 0

    def __init__(self, username, text):
        self.id = self._ID
        self._ID += 1
        self.text = text
        self.date = datetime.date
        self.time = datetime.time
        self.username = username

        self.likes = []

    # Getters and setters
    def get_text(self):
        return self.text

    def set_text(self, text):
        self.text = text

    # Add and remove like from a post
    def add_like(self, username):
        self.likes.append(Like(username))

    def remove_like(self, username):
        for like in self.likes:
            if like.username == username:
                self.likes.remove(like)

    def to_json(self):
        return ('{',
                '"id":' + '"' + str(self.id) + '",',
                '"text":' + '"' + self.text + '",',
                '"date":' + '"' + self.date + '",',
                '"time":' + '"' + self.time + '",',
                '"username":' + '"' + self.username + '"',
                '}')
