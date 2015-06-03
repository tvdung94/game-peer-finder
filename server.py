from twisted.internet.protocol import Protocol, Factory
from twisted.internet.endpoints import TCP4ServerEndpoint
from twisted.internet import reactor



# class Echo(Protocol):

# 	def connectionMade(self):
# 		self.transport.write("Connected")
# 		self.transport.loseConnection()

# 	def dataReceived(self, data):
# 		selftransport.write(data)