```
,------.            ,--.          ,--.  ,--.                                       ,--.
|  .-.  \  ,--,--.,-'  '-.,--,--. |  '--'  | ,--,--.,--.--.,--.  ,--.,---.  ,---.,-'  '-. ,---. ,--.--.
|  |  \  :' ,-.  |'-.  .-' ,-.  | |  .--.  |' ,-.  ||  .--' \  `'  /| .-. :(  .-''-.  .-'| .-. :|  .--'
|  '--'  /\ '-'  |  |  | \ '-'  | |  |  |  |\ '-'  ||  |     \    / \   --..-'  `) |  |  \   --.|  |
`-------'  `--`--'  `--'  `--`--' `--'  `--' `--`--'`--'      `--'   `----'`----'  `--'   `----'`--'

```
## Software required on your machine 🔧 ##
In order to work at this project you will need to fulfill the requirements listed below to be able to develop, test and run.

Requirements:
+ [Docker](https://www.docker.com)
+ [Git](https://git-scm.com)
+ a GitHub account
+ docker-compose (version >= 1.29)
+ IDE of your preference

### Bootstrap services

```bash
cd deploy && docker-compose -f docker-compose.yml up -d && cd -
```
### Stop services

```bash
cd deploy && docker-compose -f docker-compose.yml down && cd -
```

### To get aggregate date with json format
```bash
curl --location 'http://localhost:8090/api/data-harvester'
```

### Run test cases
```bash
cd data-harvester && mvn clean compile test && cd ..

```