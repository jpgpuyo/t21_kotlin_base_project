# T21 Android base project

This is a fork of T21 Android base project written in Kotlin.

The main purpose of this this project as petshop, and use as base project to create new projects.

The original project show a list of categories, not enough to test all features that I want to use,
so instead of showing a list of categories, this fork shows a list of users retrieved from network
and it implements the typical use case master-detail.

To retrieve the user list, the api used is the following:
https://reqres.in/api/users?page={page}

To retrieve the detail of a user, the api used is the following:
https://reqres.in/api/users/{userid}

You can find more interesting apis here:
https://reqres.in/

Summary of some things that you can find in this fork:
- Clean architecture approach
- Use single module instead of multi module project
- Organize project by feature instead of by layer
- Use kotlin coroutines instead of rxjava, encapsulated in use cases
- Use Model View Presenter instead of MVVM and LiveData. We want to be ready to kotlin-multiplatform!
- Use sampledata to show the layout preview
- Use constraintlayout

TO DO list:
- Experiment with Flow component
- Experiment with MotionLayout
- Experiment with WorkManager

# Disclaimer

There are a lot of ways to implement a master/detail app. This is not the only way, this is just a way.
I want to experiment with it. If I like it, I will use in more projects, if I don't like it I will return to batcave :)

Enjoy!

# License

The licence is Attribution-NonCommercial-NoDerivatives 4.0 International by Creative Commons

Check out more information in the CC website

https://creativecommons.org/licenses/by-nc-nd/4.0/legalcode
