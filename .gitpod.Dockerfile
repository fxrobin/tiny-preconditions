FROM gitpod/workspace-full

RUN sdk update < /dev/null
RUN sdk install java 8.0.272.hs-adpt < /dev/null
RUN java -version