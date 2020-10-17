.DEFAULT_GOAL := help
.PHONY: help
help: ## Affiche cette aide
	@grep -E '^[a-zA-Z0-9_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

.PHONY: start
start: ## Démarre toute l'application sur docker
	docker-compose up -d

.PHONY: stop
stop: ## Arrête toute l'application sur docker
	docker-compose down

.PHONY: start-db
start-db: ## Démarre le service db
	docker-compose up -d db

.PHONY: test-all
test-all: unit-test e2e-test ## Lance tous les tests
	

.PHONY: e2e-test
e2e-test: ## Lance les tests end-to-end
	cd e2e/ && yarn && yarn test

.PHONY: unit-test
unit-test: ## Lance les tests unitaires
	mvn clean test

