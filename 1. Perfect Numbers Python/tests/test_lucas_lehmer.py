import pytest
from main import lucas_lehmer


@pytest.mark.parametrize(
    "p, expected",
    [
        (2, True),
        (17, True),
        (13, True),
        (3, True),
        (5, True),
        (7, True),
        (11, False),
        (23, False),
        (29, False),
    ],
)
def test_lucas_lehmer_valid(p: int, expected: int):
    assert lucas_lehmer(p) == expected


@pytest.mark.parametrize("p", [0, -5, 0.35, "abc"])
def test_lucas_lehmer_invalid(p: int):
    with pytest.raises((ValueError, TypeError)):
        lucas_lehmer(p)
