# Sumner Bradley
from src.comment import Comment


class Post(Comment):
    _ID = 0

    def __init__(self, username, text):
        super().__init__(username, text)

        self.comments = []
        self.likes = []

    # Add and remove comments from a post
    def add_comment(self, username, comment):
        self.comments.append(Post(username, comment))

    def remove_comment(self, comment):
        self.comments.remove(comment)

    def to_json(self):
        parse = ('{',
                '"id":' + '"' + str(self.id) + '",',
                '"text":' + '"' + self.text + '",',
                '"date":' + '"' + self.date + '",',
                '"time":' + '"' + self.time + '",',
                '"username":' + '"' + self.username + '",',
                '"comments":[')

        # Add each comment to the json array
        for comment in self.comments:
            parse += comment.to_json() + ','
        parse = parse[0: len(parse) - 2]

        parse += ('],',
                '"likes":[')

        # Add each like to the json array
        for like in self.likes:
            parse += like.to_json() + ','
        parse = parse[0: len(parse) - 2]

        parse += (']',
                '}')

        return parse
