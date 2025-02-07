#
# Copyright (c) 2025, Arjuna Technologies Limited, Newcastle upon Tyne, Tyne and Wear, UK.
#

include ../settings.mk
include ../build.mk

PODMAN = podman
WGET   = wget
RM     = rm -f

IMAGE_NAME = docker.io/risbic/analysis-datashield-opal
IMAGE_TAG  = latest


all:
	$(PODMAN) build -t $(IMAGE_NAME):$(IMAGE_TAG) -f Containerfile .

push: all
	$(PODMAN) image push $(IMAGE_NAME):$(IMAGE_TAG)

clean:
	$(RM) $(MOLGENIS_ARMADILLO_JAR)

purge: clean
	$(PODMAN) $(POCMAN_IMAGE_RM) $(IMAGE_NAME):$(IMAGE_TAG)
