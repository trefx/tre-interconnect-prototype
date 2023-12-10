#
# Copyright (c) 2023, Arjuna Technologies Limited, Newcastle upon Tyne, Tyne and Wear, UK.
#

MAKE = make

all:
	$(MAKE) -C lab-request-creator all
	$(MAKE) -C lab-request-sender all
	$(MAKE) -C lab-response-processor all
	$(MAKE) -C lab-webportal all
	$(MAKE) -C lab-devel all
	$(MAKE) -C dmz-response-router all
	$(MAKE) -C sde-control all
	$(MAKE) -C sde-devel all
	$(MAKE) -C sde-request-checker all
	$(MAKE) -C sde-request-receiver all
	$(MAKE) -C sde-response-checker all
	$(MAKE) -C sde-response-sender all
	$(MAKE) -C sde-webportal all
	$(MAKE) -C sde-analysis-dispatcher all
	$(MAKE) -C anal-datashield-adaptor all
	$(MAKE) -C anal-datashield-armadillo all
#	$(MAKE) -C dev-queue-push all
#	$(MAKE) -C dev-queue-pull all

push:
	$(MAKE) -C lab-request-creator push
	$(MAKE) -C lab-request-sender push
	$(MAKE) -C lab-response-processor push
	$(MAKE) -C lab-webportal push
	$(MAKE) -C lab-devel push
	$(MAKE) -C dmz-response-router push
	$(MAKE) -C sde-control push
	$(MAKE) -C sde-devel push
	$(MAKE) -C sde-request-checker push
	$(MAKE) -C sde-request-receiver push
	$(MAKE) -C sde-response-checker push
	$(MAKE) -C sde-response-sender push
	$(MAKE) -C sde-webportal push
	$(MAKE) -C sde-analysis-dispatcher push
	$(MAKE) -C anal-datashield-adaptor push
	$(MAKE) -C anal-datashield-armadillo push
#	$(MAKE) -C dev-queue-push push
#	$(MAKE) -C dev-queue-pull push

clean:
	$(MAKE) -C lab-request-creator clean
	$(MAKE) -C lab-request-sender clean
	$(MAKE) -C lab-response-processor clean
	$(MAKE) -C lab-webportal clean
	$(MAKE) -C lab-devel clean
	$(MAKE) -C dmz-response-router clean
	$(MAKE) -C sde-analysis-dispatcher clean
	$(MAKE) -C sde-control clean
	$(MAKE) -C sde-request-checker clean
	$(MAKE) -C sde-request-receiver clean
	$(MAKE) -C sde-response-checker clean
	$(MAKE) -C sde-response-sender clean
	$(MAKE) -C sde-webportal clean
	$(MAKE) -C anal-datashield-adaptor clean
	$(MAKE) -C anal-datashield-armadillo clean
#	$(MAKE) -C dev-queue-push clean
#	$(MAKE) -C dev-queue-pull clean

purge:
	$(MAKE) -C lab-request-creator purge
	$(MAKE) -C lab-request-sender purge
	$(MAKE) -C lab-response-processor purge
	$(MAKE) -C lab-webportal purge
	$(MAKE) -C lab-devel purge
	$(MAKE) -C dmz-response-router purge
	$(MAKE) -C sde-analysis-dispatcher purge
	$(MAKE) -C sde-control purge
	$(MAKE) -C sde-request-checker purge
	$(MAKE) -C sde-request-receiver purge
	$(MAKE) -C sde-response-checker purge
	$(MAKE) -C sde-response-sender purge
	$(MAKE) -C sde-webportal purge
	$(MAKE) -C anal-datashield-adaptor purge
	$(MAKE) -C anal-datashield-armadillo purge
#	$(MAKE) -C dev-queue-push purge
#	$(MAKE) -C dev-queue-pull purge
