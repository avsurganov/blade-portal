from typing import Generic, TypeVar, Union, Optional, Any, Dict

from pydantic.generics import GenericModel

T = TypeVar("T")


class GenericResponse(GenericModel, Generic[T]):
    status: str = "success"
    details: Optional[Union[T, str, Dict[str, Any]]]
