# AMT : Project 1 - Overflow

> Authors : Gil Balsiger, Chirs Barros, Julien Béguin & Gaëtan Daubresse
> Date : 19.09.2020



## Specifications

### Pages

| Name                           | Path                      | Access          |
| ------------------------------ | ------------------------- | --------------- |
| List of questions (pagination) | `/` or `/questions`       | Everyone        |
| Question page (pagination)     | `/question/<question id>` | Everyone        |
| Register / Login               | `/login` and `/register`  | Everyone        |
| New question                   | `/new_question`           | Connected Users |
| Profile                        | `/profile/<user id>`      | Connected Users |

### Question page feature

| Name                                  | Access          |
| ------------------------------------- | --------------- |
| List answer and comments              | Everyone        |
| Provide answer                        | Connected Users |
| Add comments (to question or answer)  | Connected Users |
| Vote (to question, answer or comment) | Connected Users |

### Page layout

**Header** : Search bar

**Content** : Page's content

**Footer** : statistics (number of registered users, number of questions, etc.)

### Password policy

[To define]

### Vote policy

[To define]