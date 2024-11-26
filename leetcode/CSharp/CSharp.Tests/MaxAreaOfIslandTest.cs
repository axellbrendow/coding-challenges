using CSharp;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace CSharp.Tests;

[TestClass]
public class MaxAreaOfIslandTest
{
    [TestMethod]
    public void Test()
    {
        Assert.AreEqual(
            0,
            MaxAreaOfIsland.maxAreaOfIsland(
                new int[][]
                {
                    [0, 0, 0, 0, 0, 0, 0, 0]
                }
            )
        );

        Assert.AreEqual(
            6,
            MaxAreaOfIsland.maxAreaOfIsland(
                new int[][]
                {
                    [0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0],
                    [0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0],
                    [0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0],
                    [0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0],
                    [0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0],
                    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0],
                    [0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0],
                    [0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0],
                }
            )
        );
    }
}