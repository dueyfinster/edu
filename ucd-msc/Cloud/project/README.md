# COMP41610 Cloud Computing Project

![COMP41610 Cloud Computing Project](screenshots/homepage.png "Homepage of COMP41610 Cloud Computing Project")

A project by Neil Grogan (Student No: 13204052) to handle easy queries to [Amazon SimpleDB][] built with:

* NodeJS (Platform / Platform Dependencies [npm])
* Jam (Client Side Dependencies)
* Express (Server)
* Jade (Templating Engine)
* Bootstrap (Style)
* JQuery (Client-side logic)
* Amazon Web Services SDK (aws-sdk)

## Brief / Goals

The project goal is to create an interface that is as simple as possible for a novice user to interact with [Amazon SimpleDB][]. Amazon currently have a scratchpad application which is a bit clunky, so the brief was to create a more modern and simple way to interact with this cloud service.

## Getting Started

1. Get an [Amazon Web Services][] Account and [get API keys][]
2. Then you'll need to install [NodeJS][] for your operating systems
3. Then you need to edit `sample-awsconfig.json` and add your Amazon Web Services keys and save this file as `awsconfig.json`
4. From the root of the project directory, run `npm install` to install dependencies
5. Lastly, a simple `node app` should launch the application on [localhost:3000][]

## Testing

Tested in Chrome 33 & Firefox 27 on a Mac. Older browsers will not work as I purposely included the leaner and more modern versions of JQuery and Bootstrap.

## Troubleshooting

* Check your AWS Keys in `awsconfig.json` are correct
* You must have internet access (as we access Amazon), check your proxy etc

[Amazon Web Services]: http://aws.amazon.com/
[get API keys]: http://docs.aws.amazon.com/ses/latest/DeveloperGuide/get-aws-keys.html
[Amazon SimpleDB]: http://aws.amazon.com/simpledb/
[NodeJS]: http://nodejs.org
[localhost:3000]: http://localhost:3000/