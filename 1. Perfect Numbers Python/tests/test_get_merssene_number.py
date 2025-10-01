import pytest
from main import get_merssene_number


@pytest.mark.parametrize(
    "n, expected",
    [
        (2, 3),  # 2^2 - 1
        (3, 7),  # 2^3 - 1
        (5, 31),  # 2^5 - 1
        (7, 127),  # 2^7 - 1
    ],
)
def test_get_merssene_number_valid(n: int, expected: int):
    assert get_merssene_number(n) == expected


@pytest.mark.parametrize("n", [-1, 0, 2.5, "abc"])
def test_get_merssene_number_invalid(n: int):
    with pytest.raises((ValueError, TypeError)):
        get_merssene_number(n)
