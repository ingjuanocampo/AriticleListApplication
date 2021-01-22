# AriticleListApplication

This is simple Android - showcase. Article list application loads a list of post from [JsonPlaceHolder](https://jsonplaceholder.typicode.com/)  that the user will favorite and delete per demand locally, also the posts information will be stored into a local DB. The user can clean all the Articles and Re-sync again with the server. 
Some of the post are going to be in an un-read state, and as soon as the user see its detail, the post will be in a read state. 

Please take at look to the followinf gift: 

![Application](https://github.com/ingjuanocampo/AriticleListApplication/blob/master/articles_app.gif)

## Usecases

First, before going into the code, let's take at look how the use cases for this app are going to be: 

![UseCases](https://github.com/ingjuanocampo/AriticleListApplication/blob/master/usecases_design.png)


## Clean Architecture with MVVM 

The choosen archicture is Clean Architecture which help us to keep the amount of boilerplate code to a minimum and also demonstrates how clean the code and testeable will be. This sample follows SOLID principles, keeping the code as a cleaner as possible. 


Please take at look to the following design: 
<img src="https://github.com/ingjuanocampo/AriticleListApplication/blob/master/ci_architecture_graph.svg" 
alt="Drawing" style="width: 5px;"/>

Patterns: 
- Repository Pattern 
- Dependecy Injection Pattern 
- Delegate Pattern 
- MVVM Preseentation Pattern 


## Modules

To fulfill the clean architecture, and keep all the layers un-couple there is a module per layer. Please review the description of every module.

#### App 
App module integrates all modules, it is the main Android Project and it only contains all files related to Activities, Fragments, and Adapters. It also is in charge to resolve the dependencies of the project by creating the Main Dagger component. 
Dependecies: Presentation, Domain, Data, Cache, Remote 

#### Presentation 
The presentation is an Android Module, which is in charge of coordinate the view and model. Using an MVVM presentation pattern it holds the contracts with the model and exposes Live data to be consumed in the UI.
Dependencies: Domain

#### Domain 
The Domain is a Kotlin Module and it is designed to hold off the CORE-features of the app. This is only a logic module and should always be a Kotlin Module. This module has no dependency on other modules, it is 100% uncoupled. Also, Domain creates contracts (Repository contracts) that should be implemented in the Data layer to full fill the requirement of the features. 
Dependency: Nothing 

#### Data 
Data is Kotlin Module that implements the contracts specified in the domain layer (Repositories). With the contract, Data should coordinate all the data sources and uncouple the domain from the data layer. This module creates contracts for RemoteDataSource and LocalDataSources. 
Dependency: Domain 

#### Chache 
Cache is an Android Module that connects with local data sources like Databases, Sharepreferences, etc. In this case, we have a Room Database instance in charge of the store the post information 
Dependency: Data

#### Remote
Android Module that integrates the JsonPlaceHolder, using Retrofit it fetches all the remote required information. 
Dependency: Data

## Unit test 

With a clean architecture, the code is very easy to test in all the layers. Since most of the classes rely on contracts instead of implementation, it is easy to mock and interact with every class. 
The most important part of the app to be tested should always be Domain, since in Domain layer are all the core features and BRs. (Of course, all the code should be Tested) 

For this showcase the Domain has a 100% test coverage, please see the following image: 

<img src="https://github.com/ingjuanocampo/AriticleListApplication/blob/master/ut_screnshot.png" 
alt="Drawing" style="width: 5px;"/>

## Languages, libraries and tools used

* [Kotlin](https://kotlinlang.org/)
* [CompositeDelegateAdapter](https://github.com/ingjuanocampo/CompositeDelegateAdapter/blob/main/README.md)
* [AndroidX](https://developer.android.com/jetpack/androidx)
* [RXKotlin](https://github.com/ReactiveX/RxKotlin)
* [Dagger 2](https://github.com/google/dagger)
* [JetPack](https://developer.android.com/topic/libraries/architecture)
* [Gson](https://github.com/google/gson)
