#!/bin/bash

mkdir icons
cd icons
wget --output-document=icons_ija.zip "https://docs.google.com/uc?export=download&id=1AP3VqLheaD6lBPmgqbkQacWhfbrbg3p-"

unzip icons_ija.zip
rm icons_ija.zip
cd ..