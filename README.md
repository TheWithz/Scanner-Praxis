# Scanner-Praxis

An application used to import images from a camera, process them, and output in an OCR PDF. 

Due to a series of poor choices, the application is in java and the GUI is in javafx. 

Note: Many of the operations require JDK-9 and above.

Pre-Requirements:

ScanTailor
http://scantailor.org

Image Magick
https://www.imagemagick.org/

Tesseract OCR
https://github.com/tesseract-ocr/tesseract

PDFtk Server
https://www.pdflabs.com/tools/pdftk-server/

Mac Install:                    | Linux Install:
-----------------------------   | --------------------------
sudo port selfupdate            | sudo apt-get update
sudo port install scantailor    | sudo apt-get install scantailor
sudo port install ImageMagick   | sudo apt-get install imagemagick
sudo port install tesseract     | sudo apt-get install tesseract-ocr
sudo port install tesseract-eng | sudo apt-get install libtesseract-dev
                                | sudo apt install pdftk

### Centos 8 Install:
#### Tesseract
    dnf config-manager --add-repo https://download.opensuse.org/repositories/home:/Alexander_Pozdnyakov/CentOS_8/
    rpm --import https://build.opensuse.org/projects/home:Alexander_Pozdnyakov/public_key
    dnf install tesseract
    dnf install tesseract-langpack-deu
#### pdftk
x86_64

    yum localinstall https://www.linuxglobal.com/static/blog/pdftk-2.02-1.el7.x86_64.rpm
i686

    yum localinstall https://www.linuxglobal.com/static/blog/pdftk-2.02-1.el7.i686.rpm
#### imagemagick
    dnf install ImageMagick
#### scantailor -- currently not working
    yum localinstall http://repo.openfusion.net/centos7-x86_64//scantailor-0.9.11.1-1.of.el7.x86_64.rpm
