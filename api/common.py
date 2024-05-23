from typing import Generic, TypeVar, Union, Optional

from pydantic.generics import GenericModel

T = TypeVar("T")


class GenericResponse(GenericModel, Generic[T]):
    status: str
    details: Optional[Union[T, str]]
