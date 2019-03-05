# Podcast Player

This repo showcases a rather simplistic podcast player that currently plays podcasts from the app's storage. The code is structured such that it could be changed with minimal refactor to retrieve MP3s from an API service. When the application is no longer in the foreground, exoplayer is released. When the app is back in the foreground and a user selects a podcast it will restart the podcast from the beginning.

# UI

The UI is heavily reduced to focus on code structure. It utilizes a simple recycler view to show the 8 podcasts that are stored in the assets directory of the application. There is no player controls or playback status bar. When a podcast is selected a Toast message shows to inform the user that the Podcast has started playing.

# Code Structure

The application uses MVP to format the Main Activity into seperation of concerns. The presenter listens for lifecycle events to either init or shutdown. Upon init the Presenter tells the View (MainActivity) to create Exoplayer then load/display the podcasts. When a podcast is selected the PodcastAdapter calls the lambda callback which informs the presenter and then the presenter tells the view (MainActivity) to start playing the podcast. Majority of the classes are backed by an Interface to allow for easy mocking when writing unit tests. The only class that does not have an interface is the PodcastAdapter since it is UI. There are no unit tests at this time.

The application uses the Repository pattern along with a PodcastStatus Data Class to seperate concerns. Currently the repo relies on the AssetManager to retrieve podcast files from the assets directory. Then it maps the files into a PodcastStatus object which is added to a list and sent to the presenter. Since the repository returns a list of PodcastStatus, the code can be easily refactored to retreive podcasts from an API service and map them into the PodcastStatus. This allows for minimal changes since the access logic is wrapped up in the PodcastRepository.

# Libraries

- Timber - Logging
- Exoplayer - Media Playback

# Future Libraries

- Retrofit2 - Networking
- RxJava2 - Reactive handling of network and other events
- Glide - Image processing
