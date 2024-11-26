using System.Threading.Tasks;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;

namespace CSharpFeatures.Tests;

public interface IB
{
    Task<int> Increment(int x);
}

public abstract class B : IB
{
    public Task<int> Increment(int x) => Task.FromResult(x + 1);
}

public class A(IB b)
{
    public async Task<int> Increment(int x) => await b.Increment(x);
}

[TestClass]
public class RandomTest
{
    [TestMethod]
    public async Task Test()
    {
        var bMock = new Mock<IB>();
        bMock.Setup(b => b.Increment(1)).ReturnsAsync(1);
        var a = new A(bMock.Object);
        Assert.AreEqual(1, await a.Increment(1));
        bMock.Verify(b => b.Increment(1), Times.Once());
    }
}