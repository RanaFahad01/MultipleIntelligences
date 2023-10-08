# Multiple Intelligences Aptitude Test

This Multiple Intelligences Aptitude Test is an Aptitude test based on the [Multiple Intelligences Theory](https://en.wikipedia.org/wiki/Theory_of_multiple_intelligences).

It takes the result from the [Multiple Intelligences Aptitude Test form](https://forms.gle/aZYz7mvP2SnNtWQi8) through a text file, analyzes them and prints out the result which includes your aptitude for each intelligence type, a short description for each type and a list of professions you might be interested in pursuing.

### Features
* **Easy To Install** — All you have to do to install is extract the .zip file
* **Customizable** — Intelligences' data is stored in User-Friendly JSON files
* **Detailed Results** — Basically a career counselor if you're too broke to hire one
<br>

## Getting Started (As a User)

These instructions will get you a copy of the project up and running on your local machine for personal use. 

See [Getting Started (As a Developer)](https://github.com/RanaFahad01/MultipleIntelligences/new/main#getting-started-as-a-developer) for instructions on how to get a copy up and running for development and testing purposes.

### Prerequisites
* Java 17 or above
* Windows (Not supported on Mac/Linux yet)


### Installation
1. Download the [latest release](https://github.com/RanaFahad01/MultipleIntelligences/releases) .zip file.
2. Extract the file to a location you won't forget (I recommend the Documents or Downloads folder).

### Usage
1. Fill out the Aptitude Test Form linked [above](https://forms.gle/aZYz7mvP2SnNtWQi8).

    <img width="505" alt="Google Form for the aptitude test" src="https://github.com/RanaFahad01/MultipleIntelligences/assets/145490801/6358f5a9-f01a-43ef-9088-5a79fe2cfe71">

2. Format your answers to the questions according to the FORMATTING GUIDE, put them in the AnswersFile.txt file and save.

    <img width="505" alt="An image of the AnswersFile.txt file showing formatted answers" src="https://github.com/RanaFahad01/MultipleIntelligences/assets/145490801/8afb01d3-5a14-4dae-89ac-6da7972d7c1b">

3. Run the RunTheProgram.cmd file to get the results.

    <img width="505" alt="A picture pointing to the RunTheProgram.cmd file" src="https://github.com/RanaFahad01/MultipleIntelligences/assets/145490801/a8cf8579-1740-4548-9e54-ef8da9cfd5dc">
    
   
    <img width="749" alt="image" src="https://github.com/RanaFahad01/MultipleIntelligences/assets/145490801/6a592fde-f55a-45fb-962a-63f4b6d204c9">


**NOTE:**
If you want to customize Intelligences' information such as their jobs or descriptions, you can do so by editing the "resources/jobs.json" file.

<br>

## Getting Started (As a Developer)

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites
* Java 17 or above
* An IDE with Maven support (IntelliJ IDEA recommended)

### Installation
* #### Through GitHub:
    I. Clone the repository
  
    II. Open it using an IDE of your choice, it should automatically load in the dependencies since it's a Maven project.
* ####   Manual Installation:
    I. Download the [latest release](https://github.com/RanaFahad01/MultipleIntelligences/releases) source code .zip file.
  
    II. Extract it to a location you won't forget (I recommend the Documents folder)
  
    III. Open the folder with the IDE of your choice, it will load the dependencies in since it's a Maven project.

### Unit Testing

This project comes with unit tests. If you want to look at the tests and what they do, they are stored in the src/Test directory

<br>

## Built With

* Java
* [Maven](https://maven.apache.org/) - Dependency Management


## License

This project is licensed under the GNU General Public License v3.0 - see the [LICENSE.md](LICENSE.md) file for details
