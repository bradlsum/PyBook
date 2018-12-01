# Sumner Bradley
import datetime
from src.comment import Comment


class Post(Comment):
    id = 0

    def __init__(self):
        self.id = id
        id += 1
        self.text = ""
        self.date = datetime.date
        self.time = datetime.time
        self.username = ""

        self.comments = []
        self.likes = []

    def __init__(self, username, text):
        self.id = id
        id += 1
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
