# Sumner Bradley
import pickle
from src.user import User
from src.post import Post


class Server:
    def __init__(self):
        self.users = []
        self.posts = []

    def auth(self, username, password):  # Check if username and password are in the server
        for user in self.users:
            if user.username == username and password == user.password:
                return self.get_user_json(user)
            else:
                return "Not found"

    def add_user(self, first, last, email, username, password):
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

    # Adds a like to a specified post
    def add_like(self, post_id, user):
        for post in self.posts:
            if post.id == post_id:
                post.add_like(user)

    # Removes a like from a post
    def remove_like(self, post_id, user):
        for post in self.posts:
            if post.id == post_id:
                post.remove_like(user)

    # Add a comment to a post
    def add_comment(self, post_id, user, text):
        for post in self.posts:
            if post.id == post_id:
                post.add_comment(user, text)

    # Removes a comment from a post
    def remove_comment(self, post_id, comment_id):
        for post in self.posts:
            if post.id == post_id:
                post.remove_comment(comment_id)

    def user_json(self):
        parse = '{"users":['

        # Add each comment to the json array
        for user in self.users:
            parse += user.to_json()
            parse += ','
        parse = parse[0: len(parse) - 1]

        parse += ']}'

        return parse

    def get_user_json(self, username):
        for user in self.users:
            if user.get_username() == username:
                return user.to_json()

    def post_json(self):
        parse = '{"posts":['

        # Add each comment to the json array
        for post in self.posts:
            parse += post.to_json()
            parse += ','
        parse = parse[0: len(parse) - 2]

        parse += '}]}'

        return parse

    def save(self):
        pkl_out = open('./data.pickle', 'wb')
        pickle.dump(self, pkl_out)
        pkl_out.close()
