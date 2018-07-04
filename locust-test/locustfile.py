from locust import HttpLocust, TaskSet


def persons(l):
    l.client.get("/persons")


class UserBehavior(TaskSet):
    tasks = {persons: 2}


class WebsiteUser(HttpLocust):
    host = "http://localhost:8080"
    task_set = UserBehavior
    min_wait = 5000
    max_wait = 9000
