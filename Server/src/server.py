# Sumner Bradley
from src.user import User
from src.post import Post


class Server:
    def __init__(self):
        self.users = []
        self.posts = []

    def add_user(self, username, first, last, email, password):
        self.users.append(User(first, last, email, username, password))

    def remove_user(self, username):
        for user in self.users:
            if user.username == username:
                self.users.remove(user)

    def add_post(self, username, text):
        self.posts.append(Post(username, text))

    def remove_post(self, id):
        for post in self.posts:
            if post.id == id:
                self.posts.remove(post)
