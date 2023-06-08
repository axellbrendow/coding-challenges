from abc import ABC, abstractmethod
import unittest
from unittest import mock


class Command(ABC):
    @abstractmethod
    def execute(self):
        pass

    def name(self):
        return 'Command'


class CommandMock(Command):
    def execute(self):
        print('CommandMock execute')


class MyTestCase(unittest.TestCase):
    def test_command_child_class(self):
        command = CommandMock()
        str = command.name()
        self.assertEqual(str, 'Command')

    def test_command_class_name_method(self):
        with mock.patch.multiple(Command, __abstractmethods__=set()):
            command = Command()
            str = command.name()
            self.assertEqual(str, 'Command')

    def test_command_class_name_method_by_mocking_it(self):
        with mock.patch.multiple(Command, __abstractmethods__=set()):
            with mock.patch('__main__.Command.name') as name_mock:
                name_mock.return_value = 'Mocked Command'
                command = Command()
                str = command.name()
                self.assertEqual(str, 'Mocked Command')

    @mock.patch.multiple(Command, __abstractmethods__=set())
    @mock.patch('__main__.Command.name')
    def test_command_class_name_method_by_mocking_it_with_decorator(self, name_mock):
        name_mock.return_value = 'Mocked Command'
        command = Command()
        str = command.name()
        self.assertEqual(str, 'Mocked Command')


if __name__ == '__main__':
    unittest.main()
