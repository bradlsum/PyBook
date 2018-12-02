# Sumner Bradley
import datetime
from src.comment import Comment


class Post(Comment):
    _ID = 0

    def __init__(self):
        self.id = self._ID
        self._ID += 1
        self.text = ""
        self.date = datetime.date
        self.time = datetime.time
        self.username = ""

        self.comments = []
        self.likes = []

    def __init__(self, username, text):
        self.id = self._ID
        self._ID += 1
        self.text = text
        self.date = datetime.date
        self.time = datetime.time
        self.username = username

        self.comments = []
        self.likes = []

    # Add and remove comments from a post
    def add_comment(self, username, comment):
        self.comments.append(Post(username, comment))

    def remove_comment(self, comment):
        self.comments.remove(comment)

    def to_json
