# Genetic-Algorithm-implemented-in-Java
Implement GA in Java and provide Demo to Multi-Objective Protfolio Optimization problem

Genetic Algorithm(GA), a kind of optimization algorithm based on Darwin's theory of evolution, is one of the Heuristic Algorithms(HA) used to solve optimization in Artificial Intelligence(AI) area. Genetic Algorithm had been applied in many mathematical and engineering area, such as Portfolio Optimization, Machine Learning, Signal Processing or Self-adaptive Control, since its birth in 1970, become one of mile-stones in modern artificial intelligence technology.

The Basic GA package is in gadesign package, I provide several testing functions for basic optimization problem and GA works well on them.

To test your self-difined function, just rewirte the method: calculateFitness() in class Chrom.java; To use your own decoding and 
encoding method, just rewrite the method:　selftrans() in class Chrom.java. To run your own demo, please take a look at package: 
test. We also provide a simple Swing UI for your to handle with the GA parameters.

In this package I also provide a mapInfo package and a web package for the working of Public Map API. Here we use Baidu Map API as
Demo, and it is easy to replace it by Google Map API.

Package latlongtrans contains latitude and longtitude transformation for location data, this could be rewrited beased on your own location 
data

In WedRoot package, we provide the jsp files which used to show your own map in browser, you can run it by using tomcat 6.0.

