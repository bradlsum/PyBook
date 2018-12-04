# Sumner Bradley
import datetime
from src.like import Like


class Comment:  # Comment object that is extended by the Post object. This object also is used by the post object
    _ID = 0

    def __init__(self, username, text):
        self.id = Comment._ID
        Comment._ID += 1
        self.text = text
        self.date = datetime.date.today()
        self.time = str(datetime.datetime.now())[str(datetime.datetime.now()).find(" "):
                                                 str(datetime.datetime.now()).find(".")]
        self.username = username

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
        temp = '{"id":"' + str(self.id) + '","text":"' + self.text + '",'
        temp += '"date":"' + str(self.date) + '","time":"' + str(self.time) + '",'
        temp += '"username":"' + self.username + '"}'

        return temp
