from twisted.internet.protocol import Protocol, Factory
from twisted.internet import reactor

class Echo(Protocol):

	def connectionMade(self):
		self.transport.write("You are connected!  ^______^\n")

	def dataReceived(self, data):
		self.transport.write(data)

class EchoFactory(Factory):
	def buildProtocol(self, addr):
		return Echo()

reactor.listenTCP(8000, EchoFactory())
reactor.run()