FROM gitpod/workspace-full

# Install "software-properties-common" (for the "add-apt-repository")
RUN sudo apt-get update 
RUN sudo apt-get install -y software-properties-common

# Add the "JAVA" ppa
RUN sudo add-apt-repository -y ppa:webupd8team/java

# Install OpenJDK-8
RUN sudo apt-get update
RUN sudo apt-get install -y openjdk-8-jdk ant ca-certificates-java
RUN sudo apt-get clean;

# Fix certificate issues
RUN sudo update-ca-certificates -f;

# Setup JAVA_HOME -- useful for docker commandline
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME
