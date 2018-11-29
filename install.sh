brew install mongodb
curl -O https://fastdl.mongodb.org/osx/mongodb-osx-x86_64-3.2.21.tgz
tar -zxvf mongodb-osx-x86_64-3.2.21.tgz
sudo mkdir -p mongodb
cp -R -n mongodb-osx-x86_64-3.2.21/ mongodb
sudo mkdir -p /data/db
sudo chmod -R go+w /data/db
