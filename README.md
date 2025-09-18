# Real Estate Recommender (Genetic Algorithm)
An intelligent Android application built with Kotlin that uses a genetic algorithm to suggest real estate properties based on user preferences.

## Overview
This project is a personal academic endeavor to demonstrate the practical application of a complex optimization algorithm to a real-world problem. The app serves as a smart recommender system for a real estate company, providing a refined list of properties that are best suited to a customer's specific needs.

## Features
* Genetic Algorithm: At its core, the app utilizes a genetic algorithm to evolve a set of potential property matches.

* User Preference Input: The application takes various user preferences (e.g., type, location, number of rooms) as input.

* Intelligent Recommendations: The algorithm intelligently evaluates and ranks properties to provide the most optimal suggestions.

* Intuitive UI: A clean and easy-to-use user interface developed with Kotlin.

## How it Works
The genetic algorithm operates on a population of "chromosomes," where each chromosome represents a potential property. The algorithm performs the following steps:

* Initialization: A random population of properties is generated.

* Fitness Evaluation: A "fitness function" evaluates how well each property matches the user's preferences.

* Selection: Properties with a higher fitness score are selected to be "parents" for the next generation.

* Crossover & Mutation: Parents are combined (crossover) and slightly altered (mutation) to create new, potentially better property recommendations.

* Iteration: This process is repeated over many generations, with the population continuously evolving toward a more optimal solution.

This evolutionary approach allows the app to find excellent recommendations without needing to exhaustively search through every single property in the database.

## Technologies
* Language: Kotlin

* Platform: Android

* Algorithm: Genetic Algorithm

Getting Started
* Clone the repository: ```git clone https://github.com/Ali34Ahmad/GeneticAlgorithmApp.git```

* Open the project in Android Studio.

* Build and run the app on an Android emulator or a physical device.

Feel free to open an issue or submit a pull request if you'd like to contribute.
