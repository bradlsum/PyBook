# Sumner Bradley
from src.comment import Comment


class Post(Comment):  # Post object which is used by the Server object to store user posts
    _ID = 0

    def __init__(self, username, text):
        super().__init__(username, text)

        self.comments = []
        self.likes = []

    # Add and remove comments from a post
    def add_comment(self, username, text):
        self.comments.append(Comment(username, text))

    def remove_comment(self, id):
        for comment in self.comments:
            if comment.id == id:
                self.comments.remove(comment)

    def to_json(self):
        parse = '{"id":"' + str(self.id) + '","text":' + '"' + self.text + '",'
        parse += '"date":' + '"' + str(self.date) + '","time":"' + str(self.time) + '",'
        parse += '"username":' + '"' + self.username + '","comments":['

        # Add each comment to the json array
        for comment in self.comments:
            parse += comment.to_json() + ','
        if parse[len(parse)-1] == ',':
            parse = parse[0: len(parse) - 1]

        parse += '],"likes":['

        # Add each like to the json array
        for like in self.likes:
            parse += like.to_json()
            parse += ','
        if parse[len(parse)-1] == ',':
            parse = parse[0: len(parse) - 1]

        parse += ']}'

        return parse
