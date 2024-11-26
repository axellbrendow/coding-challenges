using System.Collections;
using System.Diagnostics;
using System.Text;

namespace CSharpFeatures;

internal static class Program // internal is the default access modifier for classes
{
    private enum Color
    {
        Yellow = 1,
        Blue,
        Green
    };

    // private is the default access modifier for fields
    private static readonly DateTime ThisDate = DateTime.Now;

    static void Main(string[] args)
    {
        Console.OutputEncoding = System.Text.Encoding.UTF8;

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ how to print");
        Console.WriteLine();

        // Format a negative integer or floating-point number in various ways.
        Console.WriteLine("Standard Numeric Format Specifiers");
        Console.WriteLine( // My locale is PT-BR, currency is reals R$
            "(C) Currency: . . . . . . . . {0:C}\n" + // -R$ 123,00
            "(D) Decimal:. . . . . . . . . {0:D}\n" + // -123
            "(E) Scientific: . . . . . . . {1:E}\n" + // -1,234500E+002
            "(F) Fixed point:. . . . . . . {1:F}\n" + // -123,45
            "(G) General:. . . . . . . . . {0:G}\n" + // -123
            "    (default):. . . . . . . . {0} (default = 'G')\n" + // -123 (default = 'G') 
            "(N) Number: . . . . . . . . . {0:N}\n" + // -123,00
            "(P) Percent:. . . . . . . . . {1:P}\n" + // -12.345,00%
            "(R) Round-trip: . . . . . . . {1:R}\n" + // -123,45
            "(X) Hexadecimal:. . . . . . . {0:X}\n", // FFFFFF85
            -123, -123.45f);

        // Format the current date in various ways.
        Console.WriteLine("Standard DateTime Format Specifiers");
        Console.WriteLine( // My locale is PT-BR
            "(d) Short date: . . . . . . . {0:d}\n" + // 24/11/2024
            "(D) Long date:. . . . . . . . {0:D}\n" + // domingo, 24 de novembro de 2024
            "(t) Short time: . . . . . . . {0:t}\n" + // 19:56
            "(T) Long time:. . . . . . . . {0:T}\n" + // 19:56:43
            "(f) Full date/short time: . . {0:f}\n" + // domingo, 24 de novembro de 2024 19:56
            "(F) Full date/long time:. . . {0:F}\n" + // domingo, 24 de novembro de 2024 19:56:43
            "(g) General date/short time:. {0:g}\n" + // 24/11/2024 19:56
            "(G) General date/long time: . {0:G}\n" + // 24/11/2024 19:56:43
            "    (default):. . . . . . . . {0} (default = 'G')\n" + //24/11/2024 19:56:43 (default = 'G')
            "(M) Month:. . . . . . . . . . {0:M}\n" + // 24 de novembro
            "(R) RFC1123:. . . . . . . . . {0:R}\n" + // Sun, 24 Nov 2024 19:56:43 GMT
            "(s) Sortable: . . . . . . . . {0:s}\n" + // 2024-11-24T19:56:43
            "(u) Universal sortable: . . . {0:u} (invariant)\n" + // 2024-11-24 19:56:43Z (invariant)
            "(U) Universal full date/time: {0:U}\n" + // domingo, 24 de novembro de 2024 22:56:43
            "(Y) Year: . . . . . . . . . . {0:Y}\n", // novembro de 2024
            ThisDate);

        // Format a Color enumeration value in various ways.
        Console.WriteLine("Standard Enumeration Format Specifiers");
        Console.WriteLine(
            "(G) General:. . . . . . . . . {0:G}\n" + // Green
            "    (default):. . . . . . . . {0} (default = 'G')\n" + // Green (default = 'G')
            "(F) Flags:. . . . . . . . . . {0:F} (flags or integer)\n" + // Green (flags or integer)
            "(D) Decimal number: . . . . . {0:D}\n" + // 3
            "(X) Hexadecimal:. . . . . . . {0:X}\n", // 00000003
            Color.Green);

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ all primitive types, casting, type assertions, instanceof");
        Console.WriteLine();

        // The C# type system -> https://learn.microsoft.com/en-us/dotnet/csharp/fundamentals/types/

        Console.WriteLine(
            @"bool	System.Boolean
byte	System.Byte
sbyte	System.SByte (8-bit signed integer)
char	System.Char
decimal	System.Decimal ±1.0 x 10^-28 to ±7.9228 x 10^28	28-29 digits	16 bytes
double	System.Double ±5.0 × 10^−324 to ±1.7 × 10^308	~15-17 digits	8 bytes
float	System.Single ±1.5 x 10^−45 to ±3.4 x 10^38	~6-9 digits	4 bytes
int	{0}
uint	System.UInt32
nint	System.IntPtr (Represents a signed integer where the bit-width is the same as a pointer.)
nuint	System.UIntPtr (Represents an unsigned integer where the bit-width is the same as a pointer.)
long	System.Int64
ulong	System.UInt64
short	System.Int16
ushort	System.UInt16

object	System.Object
string	System.String
dynamic	System.Object",
            12345.GetType()
        );

        Console.WriteLine();

        double myDouble = 0.42e2;
        Console.WriteLine($"double myDouble = 0.42e2; -> {myDouble}"); // output 42

        float myFloat = 134.45E-2f;
        Console.WriteLine($"float myFloat = 134.45E-2f; -> {myFloat}"); // output: 1.3445

        decimal myDecimal = 1m - 0.3m - 0.3m;
        Console.WriteLine($"decimal myDecimal = 1m - 0.3m - 0.3m; -> {myDecimal}"); // output: 0.4

        // verbatim string literal
        Console.WriteLine(@"
All types, including built-in numeric types such as System.Int32 (C# keyword: int),
derive ultimately from a single base type, which is System.Object (C# keyword: object). This unified type
hierarchy is called the Common Type System (CTS).
https://learn.microsoft.com/en-us/dotnet/csharp/fundamentals/types/#the-common-type-system");

        Console.WriteLine(@"
The built-in numeric types are structs, and they have fields and methods that you
can access: byte b = byte.MaxValue;");

        Console.WriteLine(@"
Anonymous types:
var v = new { Amount = 108, Message = ""Hello"" };
Console.WriteLine(v.Amount + v.Message);
");
        var strings1 = new[] { "abc" };
        string[] strings2 = ["abc"];

        Console.WriteLine();
        Console.WriteLine(
            ">>> ༼ つ ◕_◕ ༽つ strings, indexOf, cmp, replace, split, concat, lower, upper, template strings, unicode");
        Console.WriteLine();

        Console.WriteLine($"new string('a', 3) {new string('a', 3)}");
        Console.WriteLine( // abab
            $"""string.Concat(Enumerable.Repeat("ab", 2)) {string.Concat(Enumerable.Repeat("ab", 2))}""");
        Console.WriteLine($"""abcd".IndexOf("bc") {"abcd".IndexOf("bc")}""");
        Console.WriteLine($""" "aaa".IndexOf("a") {"aaa".LastIndexOf("a")}""");
        Console.WriteLine($""" "abcd".CompareTo("abc") {"abcd".CompareTo("abc")}""");
        Console.WriteLine(
            $""" "abcd".Replace("abc", "") {"abcd".Replace("abc", "")}"""); // replace all by default
        Console.WriteLine($""" "abcd".Split("") {"abcd".Split("")}""");
        Console.WriteLine($""" "a" + "a" -> {"a" + "a"}""");
        Console.WriteLine(
            $"""new StringBuilder(4).Append("ab").Append("ab") -> {new StringBuilder(4).Append("ab").Append("ab")}""");
        Console.WriteLine($"""C# uses UTF-16 by default""");
        Console.WriteLine($""" "ããã".ToUpper() -> {"ããã".ToUpper()}""");
        Console.WriteLine($""" "ÃÃÃ".ToLower() -> {"ÃÃÃ".ToLower()}""");

        Console.WriteLine(@"Characters, Symbols, UTF8 and the Unicode Miracle - Computerphile
https://www.youtube.com/watch?v=MijmeoH9LT4&ab_channel=Computerphile
UTF-8  uses 1 to 6 bytes.
UTF-16 uses 2 or 4 bytes.
UTF-32 uses 4 bytes.");
        Console.WriteLine($""" "本".Length -> {"本".Length} (the length is based on UTF-16)"""); // 1
        Console.WriteLine(
            """ "本"u8 = ReadOnlySpan<byte> (it's just a sequence of bytes in memory like byte[])""");
        Console.WriteLine($""" "本"u8.ToArray().Length -> {"本"u8.ToArray().Length}"""); // 3
        Console.WriteLine($""" "👋".Length -> {"👋".Length}"""); // 2
        Console.WriteLine($""" "👋"u8.ToArray().Length -> {"👋"u8.ToArray().Length}"""); // 4

        // Console.OutputEncoding = System.Text.Encoding.UTF8 was set on the first line of main()
        var symbols = "日👋本語";
        Console.WriteLine(System.Text.Encoding.UTF8.GetBytes(symbols).Length);

        foreach (Rune rune in symbols.EnumerateRunes())
        {
            Console.WriteLine($"character {rune} (U+{rune.Value:X4})");
        }

        for (int i = 0; i < symbols.Length; i += char.IsSurrogatePair(symbols, i) ? 2 : 1)
        {
            int codePoint = char.ConvertToUtf32(symbols, i);
            string charStr = char.ConvertFromUtf32(codePoint);
            Console.WriteLine($"character {charStr} (U+{codePoint:X4}) starts at byte position {i}");
            // character 日 (U+65E5) starts at byte position 0
            // character 👋 (U+1F44B) starts at byte position 1
            // character 本 (U+672C) starts at byte position 3
            // character 語 (U+8A9E) starts at byte position 4
        }

        Console.WriteLine($"Hi 👋, current time: {ThisDate}\n");

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ Boxing/Unboxing/Wrapper types");
        Console.WriteLine();

        Console.WriteLine(@"int value = 123; // a value type
object obj = value; // boxing
int unboxedValue = (int)obj; // unboxing");

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ assert keyword");
        Console.WriteLine();

        Console.WriteLine("Debug.Assert(true == false);");

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ MAX_INT, MIN_INT");
        Console.WriteLine();

        Console.WriteLine($"int.MaxValue = {int.MaxValue}, int.MinValue = {int.MinValue}");

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ all operators");
        Console.WriteLine();

        Console.WriteLine(
            "https://learn.microsoft.com/en-us/dotnet/csharp/language-reference/operators/#operator-precedence");

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ unsigned bit shift operator");
        Console.WriteLine();

        Console.WriteLine($"1 << 1 -> {1 << 1}"); // 1 << 1 -> 2
        Console.WriteLine($"1 >> 1 -> {1 >> 1}"); // 1 >> 1 -> 0
        Console.WriteLine($"-1 << 1 -> {-1 << 1}"); // -1 << 1 -> -2
        Console.WriteLine($"-1 >> 1 -> {-1 >> 1} the operator >> keeps the sign bit"); // -1 >> 1 -> -1
        Console.WriteLine($"-1 >>> 1 -> {-1 >>> 1:b32} (>>> unsigned bit shift operator)"); // -1 >>> 1 -> 0

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ if, else, for, while, switch");
        Console.WriteLine();

        Console.WriteLine();
        Console.WriteLine(
            ">>> ༼ つ ◕_◕ ༽つ date types (sum/subtract, convert from and to unix epoch, parse strings, ISO 8601/RFC 3339)");
        Console.WriteLine();

        DateTime now = DateTime.Now;
        Console.WriteLine("now                   " + now); // Example: 2024-11-20 11:00:15.858881362
        Console.WriteLine("now - now             " + (now - now)); // 0:00:00
        Console.WriteLine("now + 24h             " +
                          now.AddHours(24)); // Example: 2024-11-21 11:00:15.858881362
        Console.WriteLine("now - 24h             " +
                          now.AddHours(-24)); // Example: 2024-11-19 11:00:15.858881362
        DateTimeOffset nowOffset = new DateTimeOffset(now);
        Console.WriteLine("now unix secs         " + nowOffset.ToUnixTimeSeconds()); // Example: 1732111215
        Console.WriteLine("now unix millis       " +
                          nowOffset.ToUnixTimeMilliseconds()); // Example: 1732111215858
        // Parsing ISO 8601 (RFC 3339)
        string isoTimeWithoutMillis = "2024-11-20T10:50:50-03:00";
        if (
            DateTimeOffset.TryParseExact(
                isoTimeWithoutMillis,
                "yyyy-MM-ddTHH:mm:sszzz",
                System.Globalization.CultureInfo.InvariantCulture,
                System.Globalization.DateTimeStyles.None,
                out DateTimeOffset parsedTimeWithoutMillis
            )
        )
        {
            Console.WriteLine("parse without millis  " +
                              parsedTimeWithoutMillis); // 2024-11-20 10:50:50 -03:00
            Console.WriteLine("format without millis " +
                              parsedTimeWithoutMillis.ToString(
                                  "yyyy-MM-ddTHH:mm:sszzz")); // 2024-11-20T10:50:50-03:00
        }
        else Console.WriteLine("Failed to parse time without millis");

        // Parsing ISO 8601 with milliseconds (RFC 3339Nano equivalent)
        string isoTimeWithMillis = "2024-11-20T10:50:50.567-03:00";
        if (
            DateTimeOffset.TryParseExact(
                isoTimeWithMillis,
                "yyyy-MM-ddTHH:mm:ss.fffzzz",
                System.Globalization.CultureInfo.InvariantCulture,
                System.Globalization.DateTimeStyles.None,
                out DateTimeOffset parsedTimeWithMillis)
        )
        {
            Console.WriteLine("parse with millis     " +
                              parsedTimeWithMillis); // 2024-11-20 10:50:50.567 -03:00
            Console.WriteLine("format with millis    " +
                              parsedTimeWithMillis.ToString(
                                  "yyyy-MM-ddTHH:mm:ss.fffzzz")); // 2024-11-20T10:50:50.567-03:00
        }
        else
        {
            Console.WriteLine("Failed to parse time with millis");
        }

        Console.WriteLine();
        Console.WriteLine(
            ">>> ༼ つ ◕_◕ ༽つ struct (stack allocated), class (heap allocated), record (simpler class), interface");
        Console.WriteLine();

        Console.WriteLine("Same as Java");

        Console.WriteLine();
        Console.WriteLine(
            ">>> ༼ つ ◕_◕ ༽つ access modifiers (public, private, protected, internal (within .dll or .jar), file)");
        Console.WriteLine();

        Console.WriteLine("Already explained in the title");

        Console.WriteLine();
        Console.WriteLine(
            ">>> ༼ つ ◕_◕ ༽つ best practices around fields, properties, constructors, getters, and setters");
        Console.WriteLine();

        Console.WriteLine("Take a look at MyLinkedList.cs");

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ inheritance, overriding, polymorphism");
        Console.WriteLine();

        Console.WriteLine(@"class Parent
{
    public void Hi() => Console.WriteLine($""Hi from {this.GetType().Name}"");
}

class Child : Parent
{
    // new is needed because you're hiding Parent.Hi()
    public new void Hi() => Console.WriteLine($""Hi from {this.GetType().Name}"");
}

interface IInterface
{
    void Hello();
}

abstract class AbstractParent : IInterface
{
    public abstract void Hi();
    public abstract void Hello();
}

class AbstractChild : AbstractParent
{
    public override void Hi() => Console.WriteLine($""Hi from {this.GetType().Name}"");
    public override void Hello() => Console.WriteLine($""Hello from {this.GetType().Name}"");
}");

        Console.WriteLine();
        Console.WriteLine(
            ">>> ༼ つ ◕_◕ ༽つ enums https://learn.microsoft.com/en-us/dotnet/csharp/language-reference/builtin-types/enum");
        Console.WriteLine();

        Console.WriteLine(@"enum Season
{
    Spring,
    Summer,
    Autumn,
    Winter
}");

        Console.WriteLine();
        Console.WriteLine(
            ">>> ༼ つ ◕_◕ ༽つ collections (list, stack, matrix, map, set, queue, deque / doubly linked list, min heap, max heap)");
        Console.WriteLine();

        Console.WriteLine("System.Collections");
        Console.WriteLine("System.Collections.Generic");

        // LINQ Language Integrated Query returns IEnumerable<T>
        Console.WriteLine($@"string.Join(
    ',',
    from num in new List<int>() {{ 1, 2, 3 }}
    where num % 2 == 1
    select num
) -> {string.Join(
    ',',
    from num in new List<int>() { 1, 2, 3 }
    where num % 2 == 1
    select num
)}"
        ); // -> 1, 3

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ generics");
        Console.WriteLine();

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ sorting");
        Console.WriteLine();

        var arr = new int[] { 3, 2, 1 };
        Console.WriteLine($"arr {string.Join(',', arr)}");
        Array.Sort(arr, (x, y) => x - y);
        Console.WriteLine($"sorted {string.Join(',', arr)}");

        var l = new List<int> { 1, 2, 3 };
        Console.WriteLine($"list {string.Join(',', l)}");
        l.Sort((a, b) => b - a);
        Console.WriteLine($"sorted desc {string.Join(',', l)}");

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ Exceptions and errors");
        Console.WriteLine();

        Console.WriteLine("Same as Java");

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ unit tests and mocks");
        Console.WriteLine();

        Console.WriteLine("Take a look at RandomTest.cs and MyLinkedListTest.cs");

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ async/await");
        Console.WriteLine();

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ http requests");
        Console.WriteLine();

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ json parsing");
        Console.WriteLine();

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ regex and groups");
        Console.WriteLine();

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ hashing functions");
        Console.WriteLine();

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ file api");
        Console.WriteLine();

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ timers, sleep");
        Console.WriteLine();

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ concurrency, threads, virtual threads, mutexes, locks, volatile");
        Console.WriteLine();

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ How to create projects/modules/packages");
        Console.WriteLine();

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ how to install dependencies");
        Console.WriteLine();

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ How to organize files and import files");
        Console.WriteLine();

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ Memory model");
        Console.WriteLine();

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ access sqlite/mysql/postgres database avoiding sql injection");
        Console.WriteLine();

        Console.WriteLine();
        Console.WriteLine(">>> ༼ つ ◕_◕ ༽つ create an http server");
        Console.WriteLine();
    }
}