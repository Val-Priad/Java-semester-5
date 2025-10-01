import pytest
from main import get_perfect_number


@pytest.mark.parametrize(
    "p, expected", [(2, 6), (3, 28), (5, 496), (7, 8128), (13, 33550336)]
)
def test_get_perfect_number_valid(p: int, expected: int):
    assert get_perfect_number(p) == expected


@pytest.mark.parametrize("p", [0, -1, 0.25, -0.25, 4, 6, 8, 9, 10, 11, 12])
def test_get_perfect_number_invalid(p: int):
    with pytest.raises((ValueError, TypeError)):
        get_perfect_number(p)
