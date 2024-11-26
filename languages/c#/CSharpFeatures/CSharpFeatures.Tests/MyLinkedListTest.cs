using CSharpFeatures;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace CSharpFeatures.Tests;

[TestClass]
public class MyLinkedListTest
{

    [TestMethod]
    public void TestAddFront()
    {
        var list = new MyLinkedList<int>();
        list.AddFront(1);
        Assert.AreEqual(list.Size, 1);
    }
}