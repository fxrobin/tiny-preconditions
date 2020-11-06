FROM gitpod/workspace-full

USER gitpod

RUN bash ~/.sdkman/bin/sdkman-init.sh
RUN sdk update < /dev/null
RUN sdk install java 8.0.272.hs-adpt < /dev/null
RUN java -version 
