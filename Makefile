###############
#    Docker
###############

dc-up:
	cd deploy && docker-compose -f docker-compose.yml up -d && cd -
dc-down:
	cd deploy && docker-compose -f docker-compose.yml down && cd -
dc-restart: dc-down dc-up