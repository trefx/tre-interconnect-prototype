#
# Copyright (c) 2023, Arjuna Technologies Limited, Newcastle upon Tyne, Tyne and Wear, UK.
#

include ../settings.mk

PODMAN = podman
WGET   = wget
RM     = rm -f

IMAGE_NAME = $(IMAGE_REPO)/anal-datashield-armadillo
# IMAGE_TAG from settings.mk

MOLGENIS_ARMADILLO_JAR = molgenis-armadillo-3.3.0.jar
MOLGENIS_ARMADILLO_URL = https://github.com/molgenis/molgenis-service-armadillo/releases/download/v3.3.0/molgenis-armadillo-3.3.0.jar

all: $(MOLGENIS_ARMADILLO_JAR)
	$(PODMAN) build -t $(IMAGE_NAME):$(IMAGE_TAG) -f Containerfile .

$(MOLGENIS_ARMADILLO_JAR):
	$(WGET) -O $(MOLGENIS_ARMADILLO_JAR) $(MOLGENIS_ARMADILLO_URL)

push: all
	$(PODMAN) image push $(IMAGE_NAME):$(IMAGE_TAG)

clean:
	$(RM) $(MOLGENIS_ARMADILLO_JAR)

purge: clean
	$(PODMAN) image rm -i $(IMAGE_NAME):$(IMAGE_TAG)
