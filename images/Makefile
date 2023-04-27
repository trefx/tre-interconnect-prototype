#
# Copyright (c) 2023, Arjuna Technologies Limited, Newcastle upon Tyne, Tyne and Wear, UK.
#

MAKE = make

all:
	$(MAKE) -C lab-request-creator all
	$(MAKE) -C lab-request-sender all
	$(MAKE) -C lab-webportal all
	$(MAKE) -C dmz-response-router all
	$(MAKE) -C sde-analysis-adaptor all
	$(MAKE) -C sde-control all
	$(MAKE) -C sde-request-checker all
	$(MAKE) -C sde-request-receiver all
	$(MAKE) -C sde-response-checker all
	$(MAKE) -C sde-response-sender all
	$(MAKE) -C sde-webportal all

all-lab:
	$(MAKE) -C lab-request-creator all
	$(MAKE) -C lab-request-sender all
	$(MAKE) -C lab-webportal all

all-dmz:
	$(MAKE) -C dmz-response-router all

all-sde:
	$(MAKE) -C sde-analysis-adaptor all
	$(MAKE) -C sde-control all
	$(MAKE) -C sde-request-checker all
	$(MAKE) -C sde-request-receiver all
	$(MAKE) -C sde-response-checker all
	$(MAKE) -C sde-response-sender all
	$(MAKE) -C sde-webportal all

push:
	$(MAKE) -C lab-request-creator push
	$(MAKE) -C lab-request-sender push
	$(MAKE) -C lab-webportal push
	$(MAKE) -C dmz-response-router push
	$(MAKE) -C sde-analysis-adaptor push
	$(MAKE) -C sde-control push
	$(MAKE) -C sde-request-checker push
	$(MAKE) -C sde-request-receiver push
	$(MAKE) -C sde-response-checker push
	$(MAKE) -C sde-response-sender push
	$(MAKE) -C sde-webportal push

clean:
	$(MAKE) -C lab-request-creator clean
	$(MAKE) -C lab-request-sender clean
	$(MAKE) -C lab-webportal clean
	$(MAKE) -C dmz-response-router clean
	$(MAKE) -C sde-analysis-adaptor clean
	$(MAKE) -C sde-control clean
	$(MAKE) -C sde-request-checker clean
	$(MAKE) -C sde-request-receiver clean
	$(MAKE) -C sde-response-checker clean
	$(MAKE) -C sde-response-sender clean
	$(MAKE) -C sde-webportal clean

purge:
	$(MAKE) -C lab-request-creator purge
	$(MAKE) -C lab-request-sender purge
	$(MAKE) -C lab-webportal purge
	$(MAKE) -C dmz-response-router purge
	$(MAKE) -C sde-analysis-adaptor purge
	$(MAKE) -C sde-control purge
	$(MAKE) -C sde-request-checker purge
	$(MAKE) -C sde-request-receiver purge
	$(MAKE) -C sde-response-checker purge
	$(MAKE) -C sde-response-sender purge
	$(MAKE) -C sde-webportal purge
