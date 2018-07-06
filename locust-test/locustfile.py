from locust import HttpLocust, TaskSet, task


class UserBehavior(TaskSet):
    @task(6)
    def getPersons(self):
        self.client.get("/persons")


    @task(2)
    def createPerson(self):
        self.client.post("/persons", None, {"firstName": "First name", "lastName": "Last name"})


class WebsiteUser(HttpLocust):
    host = "http://localhost:8080"
    task_set = UserBehavior
    min_wait = 5000
    max_wait = 9000
