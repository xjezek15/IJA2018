#!/bin/bash

mkdir data
cd data
wget --output-document=obrazky_ija.zip "https://docs.google.com/uc?export=download&id=1AP3VqLheaD6lBPmgqbkQacWhfbrbg3p-"

unzip obrazky_ija.zip
rm obrazky_ija.zip
